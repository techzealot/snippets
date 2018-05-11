package net.invt.iot.photovoltaic.app.base.jwt;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.invt.iot.photovoltaic.util.PropertiesReader;

/**
 * Reserved claims（保留），它的含义就像是编程语言的保留字一样，属于JWT标准里面规定的一些claim。JWT标准里面定好的claim有：
 * 
 * iss(Issuser)：代表这个JWT的签发主体； sub(Subject)：代表这个JWT的主体，即它的所有人；
 * aud(Audience)：代表这个JWT的接收对象； exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间； nbf(Not
 * * Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的； iat(Issued *
 * at)：是一个时间戳，代表这个JWT的签发时间； jti(JWT ID)：是JWT的唯一标识
 * 
 * @author Administrator
 * 
 */
public class JwtHelper {

	static {
		PropertiesReader.init();
	}

	private static final String issuer = PropertiesReader.getParam("jwt.issuer");
	private static final String aud = PropertiesReader.getParam("jwt.clientId");
	private static final String base64Secret = PropertiesReader.getParam("jwt.base64secret");
	private static final long TTLMills = Long.valueOf(PropertiesReader.getParam("jwt.expiresDays")) * 24 * 3600*1000;

	/**
	 * 
	 * @param jsonWebToken
	 * @param base64Security
	 * @return
	 */
	public static Claims parseJWT(String jsonWebToken, String base64Security) {
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
					.parseClaimsJws(jsonWebToken).getBody();
			return claims;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 
	 * @param name
	 * @param userId
	 * @param role
	 * @param audience
	 * @param issuer
	 * @param TTLMillis
	 * @param base64Security
	 * @return
	 */
	public static String createJWT(String name, String userId, String role, String audience, String issuer,
			long TTLMillis, String base64Security) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		// 生成签名密钥
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// 添加构成JWT的参数
		JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT").claim("role", role).claim("unique_name", name)
				.claim("userid", userId).setIssuer(issuer).setAudience(audience)
				.signWith(signatureAlgorithm, signingKey);
		// 添加Token过期时间
		if (TTLMillis >= 0) {
			long expMillis = nowMillis + TTLMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp).setNotBefore(now);
		}

		// 生成JWT
		return builder.compact();
	}

	/**
	 * 
	 * @param name
	 *            用户唯一标识
	 * @param userId
	 *            用户ID
	 * @param role
	 *            用户角色
	 * @return
	 */
	public static String createJWT(String name, String userId, String role) {
		return createJWT(name, userId, role, aud, issuer, TTLMills, base64Secret);
	}

	public static void main(String[] args) {
		String createJWT = createJWT("", String.valueOf(12), "");
		System.out.println(createJWT);
		Claims parseJWT = parseJWT(createJWT, base64Secret);
		System.out.println(parseJWT);
		Date expiration = parseJWT.getExpiration();
		System.out.println(expiration);
	}
}