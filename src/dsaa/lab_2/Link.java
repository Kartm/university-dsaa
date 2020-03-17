package dsaa.lab_2;

class Link{
    public String ref;

    public Link(String ref) {
        this.ref=ref;
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
        return this.ref.contentEquals(other.ref);
    }
}
