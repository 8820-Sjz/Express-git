package obj;

public class TransportInfo {
    private String arrivalTime;
    private String previousPos;
    private String currentPos;
    private String transportation;
    private String parcelStatus;

    public TransportInfo(String arrivalTime, String previousPos, String currentPos, String transportation, String parcelStatus) {
        this.arrivalTime = arrivalTime;
        this.previousPos = previousPos;
        this.currentPos = currentPos;
        this.transportation = transportation;
        this.parcelStatus = parcelStatus;

    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getPreviousPos() {
        return previousPos;
    }

    public void setPreviousPos(String previousPos) {
        this.previousPos = previousPos;
    }

    public String getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(String currentPos) {
        this.currentPos = currentPos;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getParcelStatus() {
        return parcelStatus;
    }

    public void setParcelStatus(String parcelStatus) {
        this.parcelStatus = parcelStatus;
    }

    @Override
    public String toString() {
        return "TransportInfo{" +
                "arrivalTime='" + arrivalTime + '\'' +
                ", previousPos='" + previousPos + '\'' +
                ", currentPos='" + currentPos + '\'' +
                ", transportionWays='" + transportation + '\'' +
                ", parcelStatus='" + parcelStatus + '\'' +
                '}';
    }
}
