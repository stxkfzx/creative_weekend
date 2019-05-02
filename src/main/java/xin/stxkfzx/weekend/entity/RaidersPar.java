package xin.stxkfzx.weekend.entity;/***
 * ClassName:RaidersPar
 * Package:xin.stxkfzx.weekend.entity
 * Description:
 * @Date:2019/5/2 0002 12:52
 * @Author:krj@bjpowernode.com
 */


public class RaidersPar {
    private Raiders raiders;
    private RaidersContent raidersContent;

    @Override
    public String toString() {
        return "RaidersPar{" +
                "raiders=" + raiders +
                ", raidersContent=" + raidersContent +
                '}';
    }

    public Raiders getRaiders() {
        return raiders;
    }

    public void setRaiders(Raiders raiders) {
        this.raiders = raiders;
    }

    public RaidersContent getRaidersContent() {
        return raidersContent;
    }

    public void setRaidersContent(RaidersContent raidersContent) {
        this.raidersContent = raidersContent;
    }

    public RaidersPar(Raiders raiders, RaidersContent raidersContent) {

        this.raiders = raiders;
        this.raidersContent = raidersContent;
    }

    public RaidersPar() {
    }
}