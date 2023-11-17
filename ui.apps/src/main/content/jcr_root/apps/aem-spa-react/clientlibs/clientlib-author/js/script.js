(function(document, $) {
	"use strict";

    // Trigger an event on foundation content load
	$(document).on("foundation-contentloaded", function(e) {
		updateReferences();
	});

    // Trigger an event on change
    $(document).on("change", "#contentFragmentReference", function(e) {
		updateReferences($(this));
    });

})(document, Granite.$);

function updateReferences() {
	// Content Fragment path
	const contentFragmentReference = $('#contentFragmentReference').text().trim();

    var url = '/bin/global/cfPageProperties?contentFragmentPath='+contentFragmentReference;

    $.ajax({
        url:  url,
        method: 'GET',
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function(data) {
        console.log(data);
            var title = data.title;
            // content fragment title append
            $('#contentFragmentTitle').val(title);
    },
        error: function(jqXHR, textStatus, errorThrown) {
        // Handle errors
        console.error('AJAX Error:', textStatus, errorThrown);
    }
   })
}