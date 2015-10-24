// run this by entering jjs Demo4.js
// /Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home/bin/jjs src/main/resources/net/nycjava/javaone2015/Demo4.js

function addOne(i) { return i+1; }

var sum = java.util.stream.IntStream.iterate(1, addOne).
    skip(10).
    limit(20).
    peek(function(x) { print(x); }).
    sum();

print(sum);