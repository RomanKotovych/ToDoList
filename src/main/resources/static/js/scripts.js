$(document).ready(function() {
    $('.edit-btn').click(function() {
        var row = $(this).closest('tr');

        row.find('.edit-description').val(row.find('.static-description').text()).show();
        row.find('.static-description').hide();

        row.find('.edit-status').val(row.find('.static-status').text()).show();
        row.find('.static-status').hide();

        $(this).hide();
        row.find('.save-btn').show();
    });

    $('.save-btn').click(function() {
        var row = $(this).closest('tr');
        var taskId = row.find('.static-id').html();
        var newDescription = row.find('.edit-description').val();
        var newStatus = row.find('.edit-status').val();

        saveTask(taskId, newDescription, newStatus, row);
    });

    $('.delete-btn').click(function() {
        var row = $(this).closest('tr');
        var taskId = row.find('.static-id').html();

        deleteTask(taskId, row);
    });

    $('#createTaskForm').submit(function(event) {
        event.preventDefault();
        var description = $('#taskDescription').val();
        var status = $('#taskStatus').val();

        createTask(description, status);
    });
});

function saveTask(id, description, status, row) {
    $.ajax({
        url: "/api/v1.0/tasks",
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify({
            id: id,
            description: description,
            status: status
        }),
        success: function(response) {
            var newDescription = row.find('.edit-description').val();
            var newStatus = row.find('.edit-status').val();

            row.find('.static-description').text(newDescription).show();
            row.find('.edit-description').hide();

            row.find('.static-status').text(newStatus).show();
            row.find('.edit-status').hide();

            row.find('.save-btn').hide();
            row.find('.edit-btn').show();
        },
        error: function(xhr, status, error) {
            alert("Error updating task: " + xhr.responseText);
        }
    });
}

function deleteTask(id, row) {
    $.ajax({
        url: `/api/v1.0/tasks/${id}`,
        type: "DELETE",
        success: function(response) {
            row.remove();
        },
        error: function(xhr, status, error) {
            alert("Error deleting task: " + xhr.responseText);
        }
    });
}

function reloadPage() {
    const itemsPerPage = document.getElementById("itemsPerPage").value;
    window.location.href = `/tasks?page=0&size=${itemsPerPage}`;
}

function createTask(description, status) {
    $.ajax({
        url: "/api/v1.0/tasks",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            description: description,
            status: status
        }),
        success: function (response) {
            location.reload();
        },
        error: function (xhr, status, error) {
            alert("Error creating task: " + xhr.responseText);
        }
    });

}