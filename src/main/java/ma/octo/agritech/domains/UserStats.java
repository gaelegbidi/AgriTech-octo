package ma.octo.agritech.domains;

public class UserStats {

    private Integer count;

    private Integer acheteurCount;

    private Integer partenaireCount;

    private Integer publicCount;

    private Integer ongCount;

    public UserStats() {
    }

    public UserStats(Integer count, Integer acheteurCount, Integer partenaireCount, Integer publicCount, Integer ongCount) {
        this.count = count;
        this.acheteurCount = acheteurCount;
        this.partenaireCount = partenaireCount;
        this.publicCount = publicCount;
        this.ongCount = ongCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAcheteurCount() {
        return acheteurCount;
    }

    public void setAcheteurCount(Integer acheteurCount) {
        this.acheteurCount = acheteurCount;
    }

    public Integer getPartenaireCount() {
        return partenaireCount;
    }

    public void setPartenaireCount(Integer partenaireCount) {
        this.partenaireCount = partenaireCount;
    }

    public Integer getPublicCount() {
        return publicCount;
    }

    public void setPublicCount(Integer publicCount) {
        this.publicCount = publicCount;
    }

    public Integer getOngCount() {
        return ongCount;
    }

    public void setOngCount(Integer ongCount) {
        this.ongCount = ongCount;
    }
}
