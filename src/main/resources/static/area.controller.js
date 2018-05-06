
(function () {
    'use strict';

    angular
        .module('app')
        .controller('DistrictController', DistrictController);

    DistrictController.$inject = ['$http'];

    function DistrictController($http) {
        var vm = this;

        vm.divisions = [];
        vm.upazilla=[];
        vm.districts = [];
        vm.unionParisad=[];
        vm.organizations = [];
        vm.getAllDivision = getAllDivision;
        vm.getAllDistrict = getAllDistrict;
        vm.getAllUpazilla = getAllUpazilla;
        vm.getUnionParisad = getUnionParisad;
        vm.getAllOrganization = getAllOrganization;
        vm.insertReliefRecords = insertReliefRecords;


        vm.relief={organization_id:'',union_parisad_id:''};

        init();

        function init(){
            getAllDivision();
            getAllOrganization();
        }

        function getAllOrganization(){
            var url = "/areaWeb/allOrganization";
            var org = $http.get(url);
            org.then(function(response){
                vm.organizations = response.data;
            });
        }

        function getAllDivision(){
            var url = "/areaWeb/allDivision";
            var dist = $http.get(url);
            dist.then(function(response){
                vm.divisions = response.data;
            });
        }


        function getAllDistrict(id){
            var url = "/areaWeb/allDistrict/"+id;
            var dist = $http.get(url);
            dist.then(function(response){
                vm.districts = response.data;
            });
        }

        function getAllUpazilla(id){
            var url = "/areaWeb/allUpazilla/"+id;
            var dist = $http.get(url);
            dist.then(function(response){
                vm.upazilla = response.data;
            });
        }

        function getUnionParisad(id){
            var url = "/areaWeb/allUnionParisad/"+id;
            var dist = $http.get(url);
            dist.then(function(response){
                vm.unionParisad = response.data;
            });
        }

         function insertReliefRecords(relief){
            console.log(vm.relief)
             $http.post("/areaWeb/insertReliefRecord/", relief)

                .then(
                    function(response){
                        return response.data;
                    },
                    function(errResponse){
                        console.error('Error while creating relief');
                    }
                );
        }

    }
})();
