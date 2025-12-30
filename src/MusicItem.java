public abstract class MusicItem {
protected String name;
public MusicItem(String name){
    this.name = name;
}
public String getName(){
    return name;
}
public void setName(String name){
    this.name = name;
}
public abstract String getDetails();
public abstract void display();
}
