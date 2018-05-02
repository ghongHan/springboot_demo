/**
 * Created by Administrator on 2018/1/17.
 */
var LoginController = function ($scope, $http) {

    toastr.options = {
        closeButton: false,
        debug: false,
        progressBar: true,
        positionClass: "toast-top-right",
        onclick: null,
        showDuration: "300",
        hideDuration: "1000",
        timeOut: "1000",
        extendedTimeOut: "1000",
        showEasing: "swing",
        hideEasing: "linear",
        showMethod: "fadeIn",
        hideMethod: "fadeOut"
    };

    $scope.login = function () {
        var param = {
            'userName': $scope.userName,
            'password': $scope.password
        };

        $http.post('/user/login.do', param).success(function (result) {
            if(result.status == 'SUCCESS'){
                toastr.success('登录成功');
            }else{
                toastr.error('登录失败');
            }
        });
    }

};