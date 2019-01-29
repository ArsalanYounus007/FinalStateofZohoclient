package client;

import java.security.Key;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.data.ChallengeRequest;
import org.restlet.data.ChallengeResponse;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Header;
import org.restlet.data.Parameter;
import org.restlet.engine.header.ChallengeWriter;
import org.restlet.engine.security.AuthenticatorHelper;
import org.restlet.engine.util.Base64;
import org.restlet.util.Series;

public class CustomAuthenticationHelper extends AuthenticatorHelper {
    
    //This is your MarketSharp M company id.
    private static final int COMPANY_ID = 2157;
    //This is the generated user key obtained from the admin section of MarketSharp M.
    private static final String USER_KEY = "3e0edb2d-d0bc-454c-9ea3-0cba019b3008";
    //This is the associated secret key obtained from the admin section of MarketSharp M.
    private static final String SECRET_KEY = "z3o9AJ/Nrc3WlrmImttr3rhBLlG1HgQsSG48IzNFrYs=";
    
    private static final String authHeaderPrefix = String.valueOf(COMPANY_ID) + ':' + USER_KEY + ':';
    private static final String hashMessagePrefix = String.valueOf(COMPANY_ID) + USER_KEY;
    
    private Key key;
    private Mac hashFunction; 
    
    public CustomAuthenticationHelper()
    {
        super(ChallengeScheme.CUSTOM, true, false);
        try
        {
            key = new SecretKeySpec(Base64.decode(SECRET_KEY), "hmacSHA256");
            hashFunction = Mac.getInstance("hmacSHA256");
        }
        catch (Exception ex)
        {
            Logger.getLogger(CustomAuthenticationHelper.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }             
    }
    
    @Override
    public void formatResponse(ChallengeWriter cw, ChallengeResponse challenge,
            Request request, Series<Header> httpHeaders)
    {

        

        String base64EncodedCypherText = null;

        long epocTimeStampSecs = System.currentTimeMillis() / 1000;       

        try

        {

            hashFunction.init(key);

            byte[] digest = hashFunction.doFinal((hashMessagePrefix + epocTimeStampSecs).getBytes());           

            base64EncodedCypherText = Base64.encode(digest, true);

        }

        catch (Exception ex)

        {

            Logger.getLogger(CustomAuthenticationHelper.class.getName()).log(Level.SEVERE, null, ex);

            System.exit(-1);

        }       

 

              httpHeaders.add("Authorization", authHeaderPrefix + epocTimeStampSecs + ':' + base64EncodedCypherText );       

    }

}
