package server;

import Lab234.portret;
import com.google.gson.Gson;
import org.postgresql.util.PSQLException;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

interface ORMInterface<T>{

    ResultSet executeQuery(String query);

    void create();

    boolean insert(T object);

    boolean update(T object);

    boolean delete(T object);

    void dropTable();

}


abstract class AbstractORM<T> implements ORMInterface<T>{
    private Connection connection;
    private String nameTable;
    private  List<String> columns;
    private  List<Field> fields;
    private String primaryKey;
    private Field fieldPrimaryKey;
    private Map<Field, String> fieldsWithType;
    private Gson gson;
    private Class<?> aClass;


    public AbstractORM(Class<?> tClass, String url, String login, String password){

        this.aClass = tClass;
        AnnotationAnalyzer annotationAnalyzer = new AnnotationAnalyzer(tClass);
        this.nameTable=annotationAnalyzer.getNameTable();
        this.fields=annotationAnalyzer.getFields();
        this.columns=annotationAnalyzer.getNameColumns();
        this.primaryKey=annotationAnalyzer.getPrimaryKey();
        this.fieldPrimaryKey=annotationAnalyzer.getFieldPrimaryKey();
        this.fieldsWithType = new HashMap<>();
        this.gson=new Gson();
    }


    public List<String> getColumns() {
        return columns;
    }

    public List<Field> getFields() {
        return fields;
    }

    public String getNameTable() {
        return nameTable;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public Field getFieldPrimaryKey() {
        return fieldPrimaryKey;
    }

    public Map<Field, String> getFieldsWithType() {
        return fieldsWithType;
    }

    public Gson getGson() {
        return gson;
    }

    public Class<?> getaClass() {
        return aClass;
    }
}



public class ORM<T> extends AbstractORM<T> {
    Statement statement;
    public ORM(Class<?> tClass, String url, String login, String password, Statement statement){
        super(tClass, url, login, password);
        this.statement=statement;
    }


    @Override
    public void create(){
        String query = "create table "+getNameTable()+"(\n";

        for(int i=0; i<getFields().size(); i++){

            String type = getType(getFields().get(i));
            String nameTableReferences = getFieldsWithType().get(getFields().get(i));

            if(nameTableReferences!=null) {

                query += getColumns().get(i) + " " + type + " references "+nameTableReferences+" on delete cascade,\n";


            }else {
                query += getColumns().get(i) + " " + type + " ,\n";
            }

        }

        if(getPrimaryKey()!=null) {
            query += "primary key (" + getPrimaryKey() + "))";
        }else {
            query=query.substring(0,query.length()-2)+")";
        }
        System.out.println(query);
        try {
            statement.execute(query);
        }catch (PSQLException e){
//            e.printStackTrace();
            System.out.println("Таблица "+getNameTable()+" уже существует");
        }catch (Exception e){

        }
    }

    @Override
    public ResultSet executeQuery(String query) {
        try{
            return statement.executeQuery(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean insert(T object) {
        StringBuilder result = new StringBuilder();
        result.append("insert into "+getNameTable()+" values(");

        getFields().stream().forEach(
                e-> {
                    e.setAccessible(true);
                    result.append(getValue(e, object)+",");
                }
        );

        result.deleteCharAt(result.length()-1);
        result.append(");");
        try {
            System.out.println(result.toString());
            statement.executeUpdate(result.toString());
            return true;
        }catch (PSQLException e){
            System.out.println("Объект в таблице "+getNameTable()+" уже существует");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean delete(T object) {
        StringBuilder result = new StringBuilder();

        result.append("delete from "+getNameTable()+" where "+getPrimaryKey()+" = "+getValue(getFieldPrimaryKey(), object));

        try {
            statement.executeUpdate(result.toString());

            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(T object){
        StringBuilder result = new StringBuilder();

        result.append("delete from "+getNameTable()+" where "+getPrimaryKey()+" = "+getValue(getFieldPrimaryKey(), object));

        try {
            statement.executeUpdate(result.toString());
            System.out.println("Объект удален");
        }catch (SQLException e){
            e.printStackTrace();
        }
        StringBuilder finalresult = new StringBuilder();
        finalresult.append("insert into "+getNameTable()+" values(");

        getFields().stream().forEach(
                e-> {
                    e.setAccessible(true);
                    finalresult.append(getValue(e, object)+",");
                }
        );

        finalresult.deleteCharAt(finalresult.length()-1);
        finalresult.append(");");
        try {
            System.out.println(finalresult.toString());
            statement.executeUpdate(finalresult.toString());
            return true;
        }catch (PSQLException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<portret> getAll(){
        ArrayList<portret> arrayList =new ArrayList<>();
        ResultSet res;
        try {
            res = statement.executeQuery("select * from " + getNameTable() + ";");
            while (res.next()){
                String loc=res.getString(1);
                String name=res.getString(2);
                String col=res.getString(3);
                Integer size=res.getInt(4);
                Integer x=res.getInt(5);
                Integer y=res.getInt(6);
                LocalDateTime dateTime=res.getTimestamp(7).toLocalDateTime();
                arrayList.add(new portret(name,size,loc,col,x,y,dateTime.toString()));
            }
            return arrayList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
    /**
     *
     * Удаляется таблица
     */
    @Override
    public void dropTable() {
        try {
            statement.executeUpdate("drop table "+getNameTable());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    public String getType(Field field){
        Class type = field.getType();

        if(type==String.class){
            return "varchar(30)";
        }else if (type==int.class){
            return "integer";
        }else if (type==double.class){
            return "double precision";
        }else if (type==LocalDateTime.class){
            return  "timestamp";
        }else{
            AnnotationAnalyzer annotationAnalyzer = new AnnotationAnalyzer(type);

            if(annotationAnalyzer.getNameTable()!=null){
                ORM managerORM = new ORM(type,DatabaseProtocol.url,DatabaseProtocol.login,DatabaseProtocol.password,statement);

                getFieldsWithType().put(field, managerORM.getNameTable());
                if(getaClass() != type) {
                    managerORM.create();
                }
                return managerORM.getType(annotationAnalyzer.getFieldPrimaryKey());
            }else {
                return "jsonb";
            }

        }
    }



    public String getValue(Field field, T object){
        Object result =null;

        try {
            result = field.get(object);
        }catch (IllegalAccessException e){
            //e.printStackTrace();
            System.out.println("Нулевое значение");
        }

        if(result instanceof String ){
            return "'"+result+"'";
        }else if (Integer.class.isInstance(result)){
            return Integer.toString((int)result);
        }else if (Double.class.isInstance(result)){
            return Double.toString((double)result);
        }else if (LocalDateTime.class.isInstance(result)){
            return"'"+java.sql.Timestamp.valueOf((LocalDateTime) result).toString()+"'";
        }else if (result==null) {
            return null;

        }else {

            AnnotationAnalyzer annotationAnalyzer = new AnnotationAnalyzer(field.getType());
            if(annotationAnalyzer.getNameTable()!=null){

                annotationAnalyzer.getFieldPrimaryKey().setAccessible(true);


                ORM managerORM = new ORM(field.getType(),DatabaseProtocol.url,DatabaseProtocol.login,DatabaseProtocol.password,statement);
                managerORM.insert(result);

                return managerORM.getValue(annotationAnalyzer.getFieldPrimaryKey(), result);

            }else {
                return "'"+getGson().toJson(result)+"'";
            }

        }

    }


    /**
     * Этот метод собирает объект по частям из БД
     * На вход метода подаётся ResultSet, где уже хранится кортеж
     *
     */
    public T getElement(ResultSet resultSet){
        Field[] fields = getaClass().getDeclaredFields();

        try {
            Object object=getaClass().newInstance();

            Arrays.stream(fields).forEach(
                    e->{
                        if(e.getAnnotation(Column.class)!=null) {
                            e.setAccessible(true);
                            Object type = e.getType();

                            try {
                                try {
                                    if (type == int.class) {
                                        e.set(object, Integer.parseInt(resultSet.getString(e.getAnnotation(Column.class).name())));
                                    } else if (type == double.class) {
                                        e.set(object, Double.parseDouble(resultSet.getString(e.getAnnotation(Column.class).name())));
                                    } else {
                                        AnnotationAnalyzer annotationAnalyzer = new AnnotationAnalyzer(e.getType());
                                        if (annotationAnalyzer.getNameTable() != null) {


                                            annotationAnalyzer.getFieldPrimaryKey().setAccessible(true);
                                            ORM managerORM = new ORM(e.getType(),DatabaseProtocol.url,DatabaseProtocol.login,DatabaseProtocol.password,statement);
                                            ResultSet resultSet1;

                                            resultSet1 = managerORM.executeQuery("select * from "
                                                    + managerORM.getNameTable()
                                                    + " where "
                                                    + managerORM.getPrimaryKey()
                                                    + " = " + "'"+resultSet.getString(e.getAnnotation(Column.class).name())+"'"
                                            );

                                            while (resultSet1.next()){
                                                e.set(object, managerORM.getElement(resultSet1));

                                            }



                                        } else {
                                            e.set(object, getGson().fromJson(resultSet.getString(e.getAnnotation(Column.class).name()), e.getType()));
                                        }
                                    }
                                }catch (IllegalAccessException e1){
                                    e1.printStackTrace();
                                }
                            }catch (SQLException f){
                                f.printStackTrace();
                            }



                        }

                    }
            );

            return (T) object;


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    }



}