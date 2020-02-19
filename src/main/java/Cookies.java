import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Cookies {

    private Cookies() {
    }

    private static HashMap<UUID, LocalDateTime> coockies = new HashMap<>();

    public static UUID addCookie(long lifeTimeInSeconds) {
        UUID newCookie = UUID.randomUUID();
        LocalDateTime deathTime = LocalDateTime.now().plusSeconds(lifeTimeInSeconds);
        coockies.put(newCookie, deathTime);
        return newCookie;
    }

    public static boolean isNewUser(UUID cookie) {
        if (!(coockies.containsKey(cookie))) {
            return true;
        }
        if (LocalDateTime.now().isAfter(coockies.get(cookie))) {
            coockies.remove(cookie);
            return true;
        }
        return false;
    }


}
