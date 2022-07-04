package domain;

public class Person {
    private final String name;
    private final String phone;

    public Person(String info) {
        String[] split = info.split(" ");
        if (split[0].matches("[0-9]*")) {
            this.phone = split[0];
            this.name = split.length > 2 ?
                    split[1] + " " + split[2] :
                    split[1];
        } else {
            this.name = info;
            this.phone = null;
        }
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
