package com.client.jdbc.entity;

public class YoungestEldestWorker {
    private String age;
    private String name;
    private String birthday;

    public YoungestEldestWorker(String age, String name, String birthday) {
        this.age = age;
        this.name = name;
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "com.client.jdbc.entity.YoungestEldestWorker{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
