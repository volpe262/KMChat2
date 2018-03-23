package kmchatTest;

import kmchat.KMPlayer;
import org.junit.Test;

import static org.junit.Assert.*;

public class KMPlayerTest {

    @Test
    public void classTest() {
        String nick = "Nagibator666";
        KMPlayer kmplayer = new KMPlayer(nick);
        assertEquals(nick, kmplayer.getNick());
        System.out.println("Success!");
    }
}
