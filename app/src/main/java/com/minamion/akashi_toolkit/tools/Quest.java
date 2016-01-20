package com.minamion.akashi_toolkit.tools;

public class Quest {

    private String index; //编号
    private String type;  //种类
    private String period; //分类
    private String title;  //标题
    private String content;  //内容
    private String awardRanLiao;  //奖励-油
    private String awardDanYao;  //奖励-弹
    private String awardGang;  //奖励-钢
    private String awardLv;  //奖励-铝
    private String awardOther; //奖励-其他
    private String unlockIndex; //前置任务

    public String getId() {
        return index;
    }
    public void setId(String id) {
        this.index = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String id) {
        this.type = id;
    }
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String id) {
        this.period = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String id) {
        this.title = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String id) {
        this.content = id;
    }
    public String getAwardRanLiao() {
        return awardRanLiao;
    }
    public void setAwardRanLiao(String id) {
        this.awardRanLiao = id;
    }
    public String getAwardDanYao() {
        return awardDanYao;
    }
    public void setAwardDanYao(String id) {
        this.awardDanYao = id;
    }
    public String getAwardGang() {
        return awardGang;
    }
    public void setAwardGang(String id) {
        this.awardGang = id;
    }
    public String getAwardLv() {
        return awardLv;
    }
    public void setAwardLv(String id) {
        this.awardLv = id;
    }
    public String getAwardOther() {
        return awardOther;
    }
    public void setAwardOther(String id) {
        this.awardOther = id;
    }
    public String getUnlockIndex() {
        return unlockIndex;
    }
    public void setUnlockIndex(String id) {
        this.unlockIndex = id;
    }

}
