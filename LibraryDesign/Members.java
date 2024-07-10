public class Members extends Library {
    private  String memberType;
    private int maximumBookNum;
    public void addMember(String[] line){
        memberList.add(String.valueOf(memberList.size() + 1));
        memberType = line[1];
        if (getMemberType().equals("S")){
            setMaximumBookNum(2);
            Main.output += "Created new member: Student [id: " + memberList.size() +"]\n";
        }else {
            setMaximumBookNum(4);
            Main.output +="Created new member: Academic [id: " + memberList.size() +"]\n";
        }
    }
    public void setMaximumBookNum(int maximumBookNum) {
        this.maximumBookNum = maximumBookNum;
    }

    public  String getMemberType() {
        return memberType;
    }

    public int getMaximumBookNum() {
        return maximumBookNum;
    }
}
