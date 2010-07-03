$(document).ready(function(){

    // Initialization
    if(activeStartTime) {
        $('#counter').countdown({since: new Date(activeStartTime), format: 'HMS'});
        $('#counter').slideDown();
    }

    stopTimeSlice = function(id) {
        $.post(stopAction(),{activityId: id});
    }

    startTimeSlice = function(id) {
        $.post(startAction(),{activityId: id});
    }

    $('a').click(function() {

        // if active, stop the counter and deselect
        if($(this).parent().hasClass('active')) {
            $('#counter').countdown('pause');
            $('#counter').slideUp();
            $(this).parent().removeClass('active');
            stopTimeSlice($(this).next().attr('value'));
        }
        else {
            if($('.active > input')) {
                // stop previous active activity
                stopTimeSlice($('.active > input').attr('value'));

                // deselect all active activities
                $('.active').removeClass('active');
            }
            // activate the one just clicked
            $(this).parent().addClass('active');
            // and begin counting
            startTimeSlice($(this).next().attr('value'));
            
            // restart the counter
            $('#counter').countdown('destroy');
            $('#counter').countdown({since: new Date(), format: 'HMS'});
            $('#counter').slideDown();

        }

    });
});

