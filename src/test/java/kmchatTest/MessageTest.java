package kmchatTest;

import kmchat.KMPlayer;
import kmchat.Message;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MessageTest {

    @Test
    public void MessageTestSimple() {
        String nick = "Indeez";
        String formattedNick = String.format("§a%s§f", nick);
        KMPlayer kmplayer = new KMPlayer(nick);


        Map<String, String> testers = new HashMap<>();
        testers.put("Всем привет!", String.format("%s: Всем привет!", formattedNick));
        testers.put("!Есть кто дома?", String.format("%s (восклицает): Есть кто дома?", formattedNick));
        testers.put("!!А ну стоять!", String.format("%s (кричит): А ну стоять!", formattedNick));
        testers.put("!!!А-а-а-а!", String.format("%s (орёт): А-а-а-а!", formattedNick));
        testers.put("===Ну и бред…", String.format("%s (едва слышно): Ну и бред…", formattedNick));
        testers.put("==Ты это слышал?", String.format("%s (шепчет): Ты это слышал?", formattedNick));
        testers.put("=Незачем так орать.", String.format("%s (вполголоса): Незачем так орать.", formattedNick));

        for (String in : testers.keySet()) {

            Message mes = new Message(in, kmplayer);
            assertEquals(testers.get(in), mes.getResult());
        }
    }

    @Test
    public void MessageTestOOC() {
        String nick = "Indeez";
        String formattedNick = String.format("§a%s§f", nick);
        KMPlayer kmplayer = new KMPlayer(nick);

        Map<String, String> testers = new HashMap<String, String>();
        testers.put("_Всем привет!", String.format("%s (в ООС): §d(( Всем привет! ))§f", formattedNick));
        testers.put("!_Есть кто дома?", String.format("%s (восклицает в ООС): §d(( Есть кто дома? ))§f", formattedNick));
        testers.put("!!_А ну стоять!", String.format("%s (кричит в ООС): §d(( А ну стоять! ))§f", formattedNick));
        testers.put("!!!_А-а-а-а!", String.format("%s (орёт в ООС): §d(( А-а-а-а! ))§f", formattedNick));
        testers.put("===_Ну и бред…", String.format("%s (едва слышно в ООС): §d(( Ну и бред… ))§f", formattedNick));
        testers.put("==_Ты это слышал?", String.format("%s (шепчет в ООС): §d(( Ты это слышал? ))§f", formattedNick));
        testers.put("=_Незачем так орать.", String.format("%s (вполголоса в ООС): §d(( Незачем так орать. ))§f", formattedNick));

        testers.put("((Всем привет!))", String.format("%s (в ООС): §d(( Всем привет! ))§f", formattedNick));
        testers.put("!((Есть кто дома?))", String.format("%s (восклицает в ООС): §d(( Есть кто дома? ))§f", formattedNick));
        testers.put("!!((А ну стоять!))", String.format("%s (кричит в ООС): §d(( А ну стоять! ))§f", formattedNick));
        testers.put("!!!((А-а-а-а!))", String.format("%s (орёт в ООС): §d(( А-а-а-а! ))§f", formattedNick));
        testers.put("===((Ну и бред…))", String.format("%s (едва слышно в ООС): §d(( Ну и бред… ))§f", formattedNick));
        testers.put("==((Ты это слышал?))", String.format("%s (шепчет в ООС): §d(( Ты это слышал? ))§f", formattedNick));
        testers.put("=((Незачем так орать.))", String.format("%s (вполголоса в ООС): §d(( Незачем так орать. ))§f", formattedNick));

        testers.put("(( Всем привет! ))", String.format("%s (в ООС): §d(( Всем привет! ))§f", formattedNick));
        testers.put("!(( Есть кто дома? ))", String.format("%s (восклицает в ООС): §d(( Есть кто дома? ))§f", formattedNick));
        testers.put("!!(( А ну стоять! ))", String.format("%s (кричит в ООС): §d(( А ну стоять! ))§f", formattedNick));
        testers.put("!!!(( А-а-а-а! ))", String.format("%s (орёт в ООС): §d(( А-а-а-а! ))§f", formattedNick));
        testers.put("===(( Ну и бред… ))", String.format("%s (едва слышно в ООС): §d(( Ну и бред… ))§f", formattedNick));
        testers.put("==(( Ты это слышал? ))", String.format("%s (шепчет в ООС): §d(( Ты это слышал? ))§f", formattedNick));
        testers.put("=(( Незачем так орать. ))", String.format("%s (вполголоса в ООС): §d(( Незачем так орать. ))§f", formattedNick));
        for (String in : testers.keySet()) {

            Message mes = new Message(in, kmplayer);
            assertEquals(testers.get(in), mes.getResult());
        }
    }


}
