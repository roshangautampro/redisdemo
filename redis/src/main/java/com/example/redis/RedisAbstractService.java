package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class RedisAbstractService implements RedisAbstractContract {
    @Autowired
    public RedisRepo redisRepo;

    public ResponseEntity saveOrUpdateData(RedisDto redisDto) {
        Object redis = redisRepo.saveOrUpdate(redisDto.getKey(), redisDto.getValue());
        if (redis != null) {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_SAVE_SUCCESS,
                    redisDto,
                    HttpStatus.OK);
        }
        return ResponseHandler.createHttpResponse(
                "save redis failed",
                redisDto,
                HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity deleteDataByHaskey(String haskey) {

        Object redis = redisRepo.getDataByHaskeyForString(haskey);
        if (redis != null) {
            redisRepo.deleteDataByHaskey(haskey);
            return ResponseHandler.createHttpResponse(
                    String.format("Delete %s success", haskey),
                    "",
                    HttpStatus.OK);

        }

        return ResponseHandler.createHttpResponse(
                Messages.MSG_DATA_NOT_FOUND,
                haskey,
                HttpStatus.OK);

    }

    public ResponseEntity getDataByHaskey(String haskey) {
        Object redis = redisRepo.getDataByHaskeyForString(haskey);
        if (redis != null) {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_FOUND,
                    redis,
                    HttpStatus.OK);
        }
        return ResponseHandler.createHttpResponse(
                Messages.MSG_DATA_NOT_FOUND,
                haskey,
                HttpStatus.OK);

    }

    public ResponseEntity getAllData() {
        Object redis = redisRepo.getAllData();
        if (redis != null) {
            return ResponseHandler.createHttpResponse(
                    Messages.MSG_DATA_FOUND,
                    redis,
                    HttpStatus.OK);
        }
        return ResponseHandler.createHttpResponse(
                Messages.MSG_DATA_NOT_FOUND,
                "",
                HttpStatus.OK);
    }
}