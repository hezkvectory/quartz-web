# quartz-web
基于quartz实现的任务调度系统，quartz集群<br/>有很多需求需要定时调度，例如订单系统中订单30分钟内没有付款，需要自动取消订单，简单的做法就是启动一个线程<br>定时的扫描订单，看看是否超时没有支付，这样就会出现有些订单超过30分钟，定时任务还没有执行出现不严谨的现象，基于quartz实现定时任务调度，当下一个订单后，就向任务调度系统添加一条simpletrigger任务，在30分钟后自动执行，取消订单。<br/>基于此项目思路可以扩展实现，开发出来分布式任务调度系统。
