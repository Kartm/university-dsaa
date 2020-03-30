package dsaa.lab_4;

class Link implements Comparable<Link> {
    public String ref;
    public int weight;

    public Link(String ref) {
        this.ref = ref;
        weight = 1;
    }

    public Link(String ref, int weight) {
        this.ref = ref;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Link other = (Link) obj;
        if(other.ref == null) {
            return false;
        }
        return this.compareTo(other) == 0;
    }

    @Override
    public String toString() {
        return ref + "(" + weight + ")";
    }

    @Override
    public int compareTo(Link link) {
        return this.ref.compareTo(link.ref);
    }
}
