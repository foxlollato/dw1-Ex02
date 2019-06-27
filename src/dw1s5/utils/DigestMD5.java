package dw1s5.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestMD5 {
	private String senhaCriptografada;
	
	public DigestMD5(String senha){
		
		try {
			MessageDigest algoritmo = MessageDigest.getInstance("MD5"); //obtemos uma instância do algoritmo MD5
			byte[] bytes = algoritmo.digest(senha.getBytes()); //a senha é convertida para um array de bytes (hash)
			StringBuilder builder = new StringBuilder();
			for(byte b: bytes) {
				builder.append(String.format("%02X", b)); //convertemos cada byte para um valor em hexadecimal (boa prática)
			}
			this.senhaCriptografada = builder.toString(); //esta senha não pode mais ser descriptografada			
		}
		catch(NoSuchAlgorithmException erro) {
			throw new RuntimeException("Erro ao buscar algoritmo", erro);
		}
	}
	
	public String getSenhaCriptografada(){
		return this.senhaCriptografada;
	}
	
		
	}
