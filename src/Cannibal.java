public class Cannibal
{
    String name,tag ;

    public Cannibal() {}

    public Cannibal(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }


    public String getName() {
        return ConsoleColors.RED+ ConsoleColors.BLACK_BACKGROUND + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}