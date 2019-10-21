package com.wgb.utils.util.rpc.consumer.service;

import com.wgb.utils.util.rpc.Calculator;
import com.wgb.utils.util.rpc.reuqest.CalculateRpcRequest;
import com.wgb.utils.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

import static com.wgb.utils.util.constants.port.PortConstants.RPC_PORT;

/**
 * @author INNERPEACE
 */
@Slf4j
public class CalculatorRemoteImpl implements Calculator {

    @Override
    public int add(int a, int b) {
        List<String> addressList = lookupProviders("Calculator.add");
        String address = chooseTarget(addressList);
        try {
            Socket socket = new Socket(address, RPC_PORT);
            // 将请求序列化
            CalculateRpcRequest calculateRpcRequest = generateRequest(a, b);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // 将请求发给服务提供方
            objectOutputStream.writeObject(calculateRpcRequest);
            // 将响应体反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object response = objectInputStream.readObject();
            log.info("response is {}", response);
            if (response instanceof Integer) {
                return (Integer) response;
            } else {
                throw new InternalError();
            }
        } catch (Exception e) {
            log.error("fail", e);
            throw new InternalError();
        }
    }

    private CalculateRpcRequest generateRequest(int a, int b) {
        CalculateRpcRequest calculateRpcRequest = new CalculateRpcRequest();
        calculateRpcRequest.setA(a);
        calculateRpcRequest.setB(b);
        calculateRpcRequest.setMethod("add");
        return calculateRpcRequest;
    }

    private String chooseTarget(List<String> providers) {
        if (null == providers || providers.size() == 0) {
            throw new IllegalArgumentException();
        }
        return providers.get(0);
    }

    private static List<String> lookupProviders(String name) {
        if (StringUtil.isEmpty(name)) {
            return null;
        }
        return Collections.singletonList("localhost");
    }
}
