if (Hls.isSupported()) {
    var video = document.getElementById('video');
    var hls = new Hls();
    var hlssource = '';
    $('#startbt').on('click',function () {
        var inputurl = $('#hlsurl').val();
        if (!inputurl==''){
            hlssource = inputurl;
        }
        hls.loadSource(hlssource);
        hls.attachMedia(video);
        hls.on(Hls.Events.MANIFEST_PARSED, function () {
            video.play();
        })
    });
    // hls.loadSource(hlssource);
    // hls.attachMedia(video);
    // hls.on(Hls.Events.MANIFEST_PARSED, function () {
    //     video.play();
    // })
}