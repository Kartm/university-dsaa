package dsaa_jk.assignment_7;

class IntegerToStringExec implements IExecutor<Integer, String> {
    StringBuffer line = new StringBuffer();

    @Override
    public void execute(Integer elem) {
        line.append(elem + "; ");
    }

    @Override
    public String getResult() {
        line.delete(line.length() - 2, line.length());
        return line.toString();
    }
}
