package com.wgb.utils.util.rpc.provider;

import com.wgb.utils.util.rpc.Calculator;
import com.wgb.utils.util.rpc.provider.service.CalculatorImpl;
import com.wgb.utils.util.rpc.reuqest.CalculateRpcRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static com.wgb.utils.util.constants.port.PortConstants.RPC_PORT;

/**
 * @author INNERPEACE
 */
@Slf4j
public class ProviderApplication {
    public static void main(String[] args) throws IOException {
        new ProviderApplication().run();
    }

    private void run() throws IOException {
        try (ServerSocket listener = new ServerSocket(RPC_PORT)) {
            while (true) {
                try (Socket socket = listener.accept()) {
                    // 将请求反序列化
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Object object = objectInputStream.readObject();
                    log.info("request is {}", object);
                    // 调用服务
                    int result = 0;
                    if (object instanceof CalculateRpcRequest) {
                        CalculateRpcRequest calculateRpcRequest = (CalculateRpcRequest) object;
                        if ("add".equals(calculateRpcRequest.getMethod())) {
                            result = new CalculatorImpl().add(calculateRpcRequest.getA(), calculateRpcRequest.getB());
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    }
                    // 返回结果
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(result);
                    if ("0".equals(Integer.toString(result))) {
                        break;
                    }
                } catch (Exception e) {
                    log.error("fail", e);
                }
            }
        }
    }
}
