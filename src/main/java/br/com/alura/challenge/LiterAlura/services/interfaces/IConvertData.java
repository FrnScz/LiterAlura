package br.com.alura.challenge.LiterAlura.services.interfaces;

public interface IConvertData {
    <T> T fromJson(String json, Class<T> tClass);
}