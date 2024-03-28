public class Student implements Comparable<Student>{
    private String name;
    private int score;

    public Student(String name, int score){
        this.name = name;
        this.score = score;
    }

    @Override
    public int compareTo(Student another){
//        if(this.score < another.score)
//            return -1;
//        else if(this.score == another.score)
//            return 0;
//        return 1;
        return this.score - another.score;
    }

    @Override
    public String toString(){
        return String.format("Student(name: %s, score: %d)", name, score);
    }

    @Override
    public int hashCode(){
        int B = 31;

        int hash = 0;
        hash = hash * B + name.toLowerCase().hashCode();
        hash = hash * B + score;

        return hash;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Student another = (Student) o;
        return this.name.toLowerCase().equals(another.name.toLowerCase()) && this.score == another.score;
    }
}