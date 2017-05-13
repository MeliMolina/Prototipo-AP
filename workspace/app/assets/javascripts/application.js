// This is a manifest file that'll be compiled into application.js, which will include all the files
// listed below.
//
// Any JavaScript/Coffee file within this directory, lib/assets/javascripts, vendor/assets/javascripts,
// or any plugin's vendor/assets/javascripts directory can be referenced here using a relative path.
//
// It's not advisable to add code directly here, but if you do, it'll appear at the bottom of the
// compiled file.
//
// Read Sprockets README (https://github.com/rails/sprockets#sprockets-directives) for details
// about supported directives.
//
//= require jquery
//= require jquery_ujs
//= require bootstrap-datepicker
//= require jquery.min
//= require turbolinks 
//= require twitter/bootstrap
//= require skel.min
//= require cocoon
//= require bootstrap-datepicker/locales/bootstrap-datepicker.es.js
//= require jquery-ui/widgets/datepicker
//= require jquery-ui/i18n/datepicker-pt-BR
//= require highcharts
//= require Chart.bundle
//= require chartkick
//= require_tree .


$(document).on("focus", "[data-behaviour~='datepicker']", function(e){
    $(this).datepicker({"format": "dd-mm-yyyy", "weekStart": 1, "autoclose": true})
});