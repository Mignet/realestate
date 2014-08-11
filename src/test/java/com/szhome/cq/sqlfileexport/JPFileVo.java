package com.szhome.cq.sqlfileexport;

public class JPFileVo {

  private String lesson="";
  private String voice="";
  private String writen="";
  private String chinese="";
  
  public String getLesson() {
    return lesson;
  }
  public void setLesson(String lesson) {
    this.lesson = lesson;
  }
  public String getVoice() {
    return voice;
  }
  public void setVoice(String voice) {
    this.voice = voice;
  }
  public String getWriten() {
    return writen;
  }
  public void setWriten(String writen) {
    this.writen = writen;
  }
  public String getChinese() {
    return chinese;
  }
  public void setChinese(String chinese) {
    this.chinese = chinese;
  }
  @Override
  public String toString() {
    StringBuffer sb=new StringBuffer("");
    if(!"".equals(lesson))sb.append(lesson);
    if(!"".equals(voice))sb.append("  "+voice);
    if(!"".equals(writen))sb.append("  "+writen);
    if(!"".equals(chinese))sb.append("   "+chinese);
    return sb.toString();
  }
  
  
  
}

