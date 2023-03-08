## AndroidCryptoHelper

AndroidCryptoHelper is a light library that helps to handle cryptography using the Keystore module in android.

## AES Cryptography
To perform AES Cryptography, you need to call the:

### Encrypt
``val encryptedbytes = AesCryptomanager().encrypt(bytes: ByteArray, outputStream: OutputStream)``

### Decrypt
``val decryptedBytes = AesCryptomanager().decrypt(inputStream: InputStream)``