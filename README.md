# snippets
useful snippets

1、server生成keyPair(rsa private key1，uuid)=======>(rsa public key1)==>client
2、client生成keyPair(rsa private key2)=======>(rsa public key2,encryt(pwd)) server====>生成token,制定对称算法，生成算法秘钥，用公钥2加密并返回client
3、client(token,enc(secret,data))===>server
