'use strict';

angular.module('mcp.users', ['ui.bootstrap'])

    .controller('UserListController', ['$scope', 'UserService',
      function ($scope, UserService) {
        $scope.users = UserService.query();
        $scope.orderProp = 'age';
      }])

    .controller('UserDetailController', ['$scope', '$stateParams', 'UserService',
      function ($scope, $stateParams, UserService) {
        $scope.user = UserService.get({username: $stateParams.username}, function (user) {
          // $scope.mainImageUrl = user.images[0];
        });


        // TODO: extend user with an avatar
        //    $scope.setImage = function(imageUrl) {
        //      $scope.mainImageUrl = imageUrl;
        //    };
      }])

    .controller('UserProfileController', ['$scope', 'UserService', 'Session',
      function ($scope, UserService, Session) {
        $scope.user = UserService.get({username: Session.userId}, function (user) {
        });
        console.log($scope.currentUser);
      }])

    .controller('UserSignupController', ['$scope', 'UserService', '$state',
      function ($scope, UserService, $state) {
        $scope.user = {};
        $scope.message = null;
        $scope.alert = null;
        $scope.usernameAlreadyExist = true;
        $scope.signUpPromise = null;

        $scope.isValid = function (isFormValid) {
          return isFormValid
              && $scope.passwordsMatch()
              && !$scope.passwordEqualsUsername()
              && !$scope.usernameAlreadyExist;
        };

        $scope.passwordsMatch = function () {
          return $scope.user.password === $scope.user.repeatedPassword;
        };

        $scope.passwordEqualsUsername = function () {
          return $scope.user.password === $scope.user.username;
        };

        $scope.getError = function (error, minLength, maxLength, patternMsg) {
          if (angular.isDefined(error)) {
            if (error.required) {
              return "Please enter a value";
            } else if (error.minlength) {
              return "Please enter at least " + minLength + " characters";
            } else if (error.maxlength) {
              return "No more than " + maxLength + " characters";
            } else if (error.email) {
              return "Please enter a valid email address";
            } else if (error.pattern) {
              return patternMsg;
            }
          }

        };

        $scope.resolveUniqueUsername = function () {
          if (!angular.isDefined($scope.user.username)) {
            $scope.usernameAlreadyExist = true;
            return;
          }
          return UserService.isUnique({username: $scope.user.username}, function (data) {
            //console.log(data, $scope.usernameAlreadyExist);
            $scope.usernameAlreadyExist = data.usernameExist;
          });
        };

        $scope.$watch("user.username",
            function (newValue, oldValue, scope) {
              if (newValue !== oldValue) {
                //console.log(newValue, oldValue);
                scope.resolveUniqueUsername();
              }
            }
        );

        $scope.sendRequest = function () {
          $scope.alert = null;
          $scope.message = "Sending request for access.";
          delete $scope.user.repeatedPassword;
          $scope.signUpPromise = UserService.signUp($scope.user, function (data) {
            $scope.message = null;
            $state.transitionTo("public.joinConfirmation");
          }, function (error) {
            $scope.message = null;
            $scope.alert = "Argh! An error occured on the server :(";
          });
        };

      }])

    .controller('UserResetPasswordController', ['$scope', '$stateParams', 'AuthService', '$controller',
      function ($scope, $stateParams, AuthService, $controller) {

        // Inherit password field behavior from similar controller
        $controller('UserSignupController', {$scope: $scope}); //This works
        // Override as we do not require a unique username
        $scope.resolveUniqueUsername = function () {
          return true;
        };
        $scope.isValid = function (isFormValid) {
          //console.log(isFormValid, $scope.passwordsMatch(), !$scope.passwordEqualsUsername());
          return isFormValid
              && $scope.passwordsMatch()
              && !$scope.passwordEqualsUsername();
        };

        $scope.busyPromise = null;
        $scope.viewState = 'supplyPassword';
        $scope.user.username = $stateParams.username;

        $scope.changePassword = function (newPassword) {
          $scope.busyPromise = AuthService.resetPassword(
              $stateParams.username,
              $stateParams.activationId,
              newPassword).then(
              function () {
                $scope.viewState = "success";
              },
              function (error) {
                // Error handler code
                //console.log("Error during reset of password: ", error);
                $scope.alert = "Whoops! Something went wrong: (" + error.status + ") " + error.statusText;
                $scope.viewState = "expired";
              });
        };
      }
    ])

    .controller('UserActivationController', ['$scope', '$stateParams', 'UserService',
      function ($scope, $stateParams, UserService) {
        //console.log("Activate " + $stateParams.username);
        $scope.busyPromise = null;
        $scope.accountActivated = null;
        $scope.viewState = 'loading';

        $scope.busyPromise = UserService.activateAccount({
          username: $stateParams.username,
          activationId: $stateParams.activationId
        }, {/*an empty data payload*/},
            function (data) {
              $scope.viewState = data.accountActivated ? 'accountActivated' : 'accountNotActivated';
            },
            function (error) {
              //console.log(error);
              $scope.viewState = 'error';
            });

      }
    ])


    // UserContext
    // holds info about: 
    // - the current logged in user 
    // - the users list of organization memberships
    .service("UserContext", ["UserService", function (UserService) {

        // current user
        var currentUser = null;

        // Organizations that the current user is a member of
        var organizationMemberships = [];

        this.reset = function () {
          currentUser = null;
          organizationMemberships = null;
        };

        this.refresh = function () {
          updateOrganizationMemberships();
        };

        this.setCurrentUser = function (aUser) {
          //console.trace();
          //console.log('Current user:', aUser );
          currentUser = aUser;
          this.refresh();
          console.log('Current organizationMemberships:', organizationMemberships);
        };

        this.currentUser = function () {
          return currentUser;
        };

        this.organizationMemberships = function () {
          return organizationMemberships;
        };

        this.isAdminMemberOf = function (organizationId) {
          for (var i = 0; i < organizationMemberships.length; i++) {
            if (organizationId === organizationMemberships[i].organizationId) {
              return true;
            }
          }
          return false;
        };

        var updateOrganizationMemberships = function () {
          organizationMemberships = currentUser ? UserService.queryOrganizationMeberships({username: currentUser.name}) : [];
          
//          // reset currentOrganization if no longer on list
//          if (this.currentOrganization !== null) {
//            this.setCurrentOrganization(this.getOrganizationById(this.currentOrganization.organizationId));
//          }
        };



      }])

    ;
