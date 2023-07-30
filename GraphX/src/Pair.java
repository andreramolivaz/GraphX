public class Pair<U, V> {
    public  U first;
    public  V second;

    protected Pair(U first, V second)
    {
        this.first = first;
        this.second = second;
    }

    public void setFirst(U first){
        this.first = first;
    }

    public void setSecond(V second){
        this.second = second;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) o;

        if (!first.equals(pair.first)) {
            return false;
        }
        return second.equals(pair.second);
    }

    @Override
    public int hashCode()
    {
        return 31 * first.hashCode() + second.hashCode();
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static <U, V> Pair <U, V> of(U a, V b)
    {
        return new Pair<>(a, b);
    }
}