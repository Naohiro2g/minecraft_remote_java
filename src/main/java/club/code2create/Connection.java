package club.code2create;

        import java.io.*;
        import java.net.Socket;
        import java.util.Arrays;
        import java.util.stream.Collectors;

        public class Connection {
            private Socket socket;
            private PrintWriter out;
            private BufferedReader in;
            private String lastSent;
            private boolean debug;

            public Connection(String address, int port, boolean debug) throws IOException {
                this.socket = new Socket(address, port);
                this.socket.setSoTimeout(60000);
                this.out = new PrintWriter(socket.getOutputStream(), true);
                this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                this.debug = debug;
            }

            public void drain() throws IOException {
                while (in.ready()) {
                    String data = in.readLine();
                    if (debug) {
                        System.err.println("Drained Data: <" + data.trim() + ">");
                        System.err.println("Last Message: <" + lastSent.trim() + ">");
                    }
                }
            }

            public void send(String f, Object... data) throws IOException {
                String s = f + "(" + Arrays.stream(data).map(Object::toString).collect(Collectors.joining(","));
//                System.out.println("Before removing quotes: " + s); // 引用符除去前にプリント
                s = removeQuotes(s) + ")\n";
                System.out.print("Sending command: " + s); // 送信される文字列を標準出力に表示
                _send(s);
            }

            private String removeQuotes(String input) {
                return input.replace("\"", "");
            }

            private void _send(String s) throws IOException {
                drain();
                lastSent = s;
                out.println(s);
            }

            public String receive() throws IOException, RequestError {
                String s = in.readLine().trim();
                if ("Fail".equals(s)) {
                    throw new RequestError(lastSent.trim() + " failed");
                }
                return s;
            }

            public String sendReceive(String f, Object... data) throws IOException, RequestError {
                send(f, data);
                return receive();
            }

            public static class RequestError extends Exception {
                public RequestError(String message) {
                    super(message);
                }
            }
        }