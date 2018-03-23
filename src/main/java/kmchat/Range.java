package kmchat;


public class Range {
    private boolean global = false;
    private boolean OOC = false;
    private String rawMessage;
    private String desc = "";
    private String distanceDesc = "default";

    public Range(String mes) {
        if (mes.startsWith("^")) {
            global = true;
            mes = mes.substring(1);
        } else if (mes.startsWith("!!!")) {
            distanceDesc = "strongshout";
            desc = " (орёт)";
            mes = mes.substring(3);
        } else if (mes.startsWith("!!")) {
            distanceDesc = "shout";
            desc = " (кричит)";
            mes = mes.substring(2);
        } else if (mes.startsWith("!")) {
            distanceDesc = "weakshout";
            desc = " (восклицает)";
            mes = mes.substring(1);
        } else if (mes.startsWith("===")) {
            distanceDesc = "strongwhisper";
            desc = " (едва слышно)";
            mes = mes.substring(3);
        } else if (mes.startsWith("==")) {
            distanceDesc = "whisper";
            desc = " (шепчет)";
            mes = mes.substring(2);
        } else if (mes.startsWith("=")) {
            distanceDesc = "weakwhisper";
            desc = " (вполголоса)";
            mes = mes.substring(1);
        }

        if (mes.startsWith("_") || (mes.startsWith("((") && mes.endsWith("))"))) {
            if (mes.startsWith("((")) {
                mes = mes.substring(2, mes.length() - 2);
                mes = mes.trim();
            } else {
                mes = mes.substring(1);
            }
            ООС = true;
            if (desc.length() == 0)
                desc = " (в ООС)";
            else
                desc = desc.substring(0, desc.length() - 1) + " в ООС)";

        }
        setRawMessage(mes);
    }


    public boolean ooc() {
        return OOC;
    }

    public boolean global() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public String getDesc() {
        return desc;
    }

    public String getDistanceDesc() {
        return distanceDesc;
    }

    public void setDistanceDesc(String distanceDesc) {
        this.distanceDesc = distanceDesc;
    }

    public String getRawMessage() {
        return rawMessage;
    }

    private void setRawMessage(String rawMessage) {
        this.rawMessage = rawMessage;
    }

}
