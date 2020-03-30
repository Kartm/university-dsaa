package dsaa_jk.assignment_3b.task_3;

class Official {
    String name;
    int timeLeft = 0;
    Customer currentCustomer = null;

    public Official(String name) {
        this.name = name;
    }

    public void assignCustomer(Customer customer) {
        currentCustomer = customer;
        timeLeft = customer.neededTime;
    }

    public void tick() {
        if(this.timeLeft > 0) {
            this.timeLeft--;
        }
        if(isFree()) {
            currentCustomer = null;
        }
    }

    public boolean isFree() {
        return timeLeft == 0;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
