class Player {
    private final int id;
    private final String name;
    private final int age;
    private final String position;
    private final String asalNegara;
    private final String status;
    private final String grade;
    private final double gaji;

    public Player(int id, String name, int age, String position, String asalNegara, String status, String grade, double gaji) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.position = position;
        this.asalNegara = asalNegara;
        this.status = status;
        this.grade = grade;
        this.gaji = gaji;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

    public String getAsalNegara() {
        return asalNegara;
    }

    public String getStatus() {
        return status;
    }

    public String getGrade() {
        return grade;
    }

    public double getGaji() {
        return gaji;
    }

    @Override
    public String toString() {
        return "Player [ID=" + id + ", Name=" + name + ", Age=" + age + ", Position=" + position +", Asal Negara=" + asalNegara + ", Status=" + status + ", Grade=" + grade + ", Gaji=" + gaji + "]";
    }
}