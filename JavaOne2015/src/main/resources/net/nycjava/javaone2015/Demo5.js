// run this by entering jjs -fx Demo5.js
// /Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home/bin/jjs -fx src/main/resources/net/nycjava/javaone2015/Demo5.js

var Button = javafx.scene.control.Button;
var StackPane = javafx.scene.layout.StackPane;
var Scene = javafx.scene.Scene;

function start(primaryStage) {
    primaryStage.title = "Hello World!";
    var button = new Button();
    button.text = "Say 'Hello World'";
    button.onAction = function() { print("Hello World!"); };
    var root = new StackPane();
    root.children.add(button);
    primaryStage.scene = new Scene(root, 300, 250);
    primaryStage.show();
}