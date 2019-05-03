package xin.stxkfzx.weekend.util;

import com.auth0.jwt.exceptions.JWTDecodeException;
import io.jsonwebtoken.*;
import org.joda.time.DateTime;
import xin.stxkfzx.weekend.entity.JwtConstants;
import xin.stxkfzx.weekend.entity.UserBase;
import xin.stxkfzx.weekend.exception.UnLoginException;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * JWT工具类
 *
 * @author VicterTian
 * @version V1.0
 * @date 2019/4/13
 */
@SuppressWarnings("all")
public class JwtUtils {
    /**
     * 生成Token
     *
     * @param userBase      userBase
     * @param privateKey    privateKey
     * @param expireMinutes expireMinutes
     * @return Token字符串
     */
    public static String generateToken(UserBase userBase, PrivateKey privateKey, int expireMinutes) {
        return Jwts.builder()
                .claim(JwtConstants.JWT_KEY_ID, userBase.getId())
                .claim(JwtConstants.JWT_KEY_USER_NAME, userBase.getNickName())
                .setExpiration(DateTime.now().plusHours(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    /**
     * 生成Token
     *
     * @param userBase      userBase
     * @param privateKey    privateKey
     * @param expireMinutes expireMinutes
     * @return Token字符串
     * @throws Exception
     */
    public static String generateToken(UserBase userBase, byte[] privateKey, int expireMinutes) throws Exception {
        return Jwts.builder()
                .claim(JwtConstants.JWT_KEY_ID, userBase.getId())
                .claim(JwtConstants.JWT_KEY_USER_NAME, userBase.getNickName())
                .setExpiration(DateTime.now().plusHours(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.ES256, RsaUtils.getPrivateKey(privateKey))
                .compact();
    }

    /**
     * 公钥解析Token
     *
     * @param publicKey publicKey
     * @param token     token
     * @return Jws<Claims>
     */
    private static Jws<Claims> parseToken(PublicKey publicKey, String token) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }


    /**
     * 公钥解析Token
     *
     * @param publicKey publicKey
     * @param token     token
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parseToken(byte[] publicKey, String token) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey)).parseClaimsJws(token);
    }


    /**
     * 从Token中获取用户信息（使用公钥解析）
     *
     * @param publicKey publicKey
     * @param token     token
     * @return UserBase
     */
    public static UserBase getUserBase(PublicKey publicKey, String token) {
        try {
            Jws<Claims> claimsJws = parseToken(publicKey, token);
            Claims body = claimsJws.getBody();
            return new UserBase(
                    ObjectUtils.toInt(body.get(JwtConstants.JWT_KEY_ID)),
                    ObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME)),
                    ObjectUtils.toInt(body.get(JwtConstants.JWT_KEY_USER_STAT))
            );
        }catch (JWTDecodeException j) {
            throw new UnLoginException("token无效，请重新登录");
        }catch (ExpiredJwtException e){
            throw new UnLoginException("token过期，请重新登录");
        }
    }

    /**
     * 从Token中获取用户信息（使用公钥解析）
     *
     * @param publicKey publicKey
     * @param token     token
     * @return UserBase
     * @throws Exception
     */
    public static UserBase getUserBase(byte[] publicKey, String token) throws Exception {
        Jws<Claims> claimsJws = parseToken(publicKey, token);
        Claims body = claimsJws.getBody();
        return new UserBase(
                ObjectUtils.toInt(body.get(JwtConstants.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME)),
                ObjectUtils.toInt(body.get(JwtConstants.JWT_KEY_USER_STAT))
        );
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public static UserBase getInfoFromToken(String token, PublicKey publicKey) {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return new UserBase(
                ObjectUtils.toInt(body.get(JwtConstants.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME))
        );
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public static UserBase getInfoFromToken(String token, byte[] publicKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        return new UserBase(
                ObjectUtils.toInt(body.get(JwtConstants.JWT_KEY_ID)),
                ObjectUtils.toString(body.get(JwtConstants.JWT_KEY_USER_NAME))
        );
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥
     * @return Jws<Claims>
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥字节数组
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, byte[] publicKey) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey))
                .parseClaimsJws(token);
    }
}
