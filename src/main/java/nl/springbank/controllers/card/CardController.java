package nl.springbank.controllers.card;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import nl.springbank.exceptions.AuthenticationError;
import nl.springbank.exceptions.InvalidParamValueError;
import nl.springbank.exceptions.NoEffectError;
import nl.springbank.exceptions.NotAuthorizedError;
import nl.springbank.objects.AuthenticationObject;

/**
 * @author Tristan de Boer
 * @author Sven Konings
 */
@JsonRpcService("/api/card")
public interface CardController {
    /**
     * Attempts to unblock a card.
     *
     * @param authToken The authentication token, obtained with getAuthToken
     * @param iBAN The number of the bank account
     * @param pinCard The number of the pinCard
     *
     * @return An empty dictionary if successful
     *
     * @throws InvalidParamValueError One or more parameter has an invalid value. See message.
     * @throws NotAuthorizedError The authenticated user is not authorized to perform this action.
     * @throws NoEffectError If the card is not blocked this method will have no effect.
     */
    @JsonRpcErrors({
            @JsonRpcError(exception = InvalidParamValueError.class, code = 418),
            @JsonRpcError(exception = NotAuthorizedError.class, code = 419),
            @JsonRpcError(exception = NoEffectError.class, code = 420)
    })
    Object unblockCard(
            @JsonRpcParam("authToken") String authToken,
            @JsonRpcParam("iBAN") String iBAN,
            @JsonRpcParam("pinCard") String pinCard
    ) throws InvalidParamValueError,
            NotAuthorizedError,
            NoEffectError;
}
