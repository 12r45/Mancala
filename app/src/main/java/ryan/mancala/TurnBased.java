package ryan.mancala;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
/**
 * Created by ryan on 5/1/2015.
 */
public class TurnBased implements ResultCallback{
    private static final String TAG = TurnBased.class.getCanonicalName();
    private GoogleApiClient googleApiClient;

    private TurnBasedMatch match;

    public TurnBased(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;

    }

}
