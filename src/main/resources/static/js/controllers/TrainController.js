'use strict';

/**
 * TrainController
 * @constructor
 */
var TrainController = function ($scope, $http) {
    $scope.train = {};
    $scope.editMode = false;

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

    $scope.$watch('$viewContentLoading', function (event, viewConfig) {
        toastr.error('页面加载中...');
    });

    $scope.$watch('$viewContentLoaded', function (event) {
        toastr.success('页面加载成功');
    });

    $scope.fetchTrainsList = function () {
    /**
        //数据从数据库中直接读取
        $http.get('train/allTrains').success(function (trainList) {
            $scope.trains = trainList.data.content;
        });
    */
        //数据从redis读取
        $http.get('redis/getZSet?key=get_trains&start=0&end=1').success(function (trainList) {
            $scope.trains = trainList;
        });
    };

    /**
     * 添加车辆
     * @param train
     */
    $scope.addNewTrain = function (train) {
        var param = {
            'id': 13,
            'name': train.name,
            'speed': train.speed,
            'diesel': train.diesel,
            'stationId': 1
        };

        $http.post('train/addTrain', param).success(function (result) {
            if (result.status == 'SUCCESS') {
                toastr.success('添加成功');
                $scope.fetchTrainsList();
            }
        }).error(function () {
            $scope.setError('Could not add a new train');
        });
    };

    $scope.updateTrain = function (train) {
        $scope.resetError();

        $http.put('trains/updateTrain', train).success(function () {
            $scope.fetchTrainsList();
            $scope.train.name = '';
            $scope.train.speed = '';
            $scope.train.diesel = false;
            $scope.editMode = false;
        }).error(function () {
            $scope.setError('Could not update the train');
        });
    };

    $scope.editTrain = function (train) {
        $scope.resetError();
        $scope.train = train;
        $scope.editMode = true;
    };

    $scope.removeTrain = function (id) {
        $scope.resetError();

        $http.delete('trains/removeTrain/' + id).success(function () {
            $scope.fetchTrainsList();
        }).error(function () {
            $scope.setError('Could not remove train');
        });
        $scope.train.name = '';
        $scope.train.speed = '';
    };

    $scope.removeAllTrains = function () {
        $scope.resetError();

        $http.delete('trains/removeAllTrains').success(function () {
            $scope.fetchTrainsList();
        }).error(function () {
            $scope.setError('Could not remove all trains');
        });

    };

    $scope.resetTrainForm = function () {
        $scope.resetError();
        $scope.train = {};
        $scope.editMode = false;
    };

    $scope.resetError = function () {
        $scope.error = false;
        $scope.errorMessage = '';
    };

    $scope.setError = function (message) {
        $scope.error = true;
        $scope.errorMessage = message;
    };

    $scope.fetchTrainsList();

    $scope.predicate = 'id';
};