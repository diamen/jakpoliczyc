module.exports = function (grunt) {

    grunt.initConfig({
       pkg: grunt.file.readJSON('package.json'),

       clean: ['sass/build.scss', 'styles/main.css', 'styles/main.css.map'],

       concatScss: {
            dist: {
               src: [
                   'sass/*.scss'
               ],
               dest: 'sass/build.scss'
            }
       },

       sass: {
            dist: {
                options: {
                    style: 'expanded'
                },
                files: {
                    'styles/external/tmp.css': 'sass/build.scss'
                }
            }
       },

       concatCss: {
            dist: {
                src: [
                    'styles/external/*.css'
                ],
                dest: 'styles/main.css'
            }
       },

        purifycss: {
           options: {},
           target: {
               src: ['index.html', 'views/*.html', 'views/templates/*.html', 'scripts/directives/*.js', 'libraries/ui-bootstrap/*.js'],
               css: ['styles/main.css'],
               dest: 'styles/main.css'
           }
        },

       autoprefixer: {
            dist: {
                options: {
                    diff: false
                },
                src: 'styles/main.css'
            }
       },

       cssmin: {
           target: {
               files: [{
                   src: 'styles/main.css',
                   dest: 'styles/main.min.css'
               }]
           }
       },

        ngtemplates: {
          jakPoliczycApp: {
              src:  ['views/*.html', 'views/partials/*.html', 'views/templates/*.html'],
              dest: 'scripts/template.js'
          }
        },

        concatJS: {
            dist: {
                src: [
                    /* Libraries */
                    'libraries/angular/angular.min.js' ,
                    'libraries/mspace/mspace.js' ,
                    'libraries/ui-bootstrap/ui-bootstrap-tpls-2.0.0.min.js' ,
                    'libraries/ui-router/angular-ui-router.min.js' ,
                    'libraries/angular-mocks/angular-mocks.js' ,
                    'libraries/angular-sanitize/angular-sanitize.js' ,
                    'libraries/angular-ui-tree/dist/angular-ui-tree.js' ,
                    'libraries/underscore/underscore.js',

                    /* Browser support */
                    'scripts/browsersupport.js' ,

                    /* Common */
                    'scripts/languages/PL.js' ,

                    /* Main */
                    'scripts/app.js' ,

                    /* Generated */
                    'scripts/template.js',

                    /* Config */
                    'scripts/config/bootstrap.js' ,
                    'scripts/config/routersConfig.js' ,
                    'scripts/config/treeConfig.js' ,

                    /* Dane testowe */
                    'scripts/mockdata.js' ,

                    /* Controllers */
                    'scripts/controllers/controller.js' ,
                    'scripts/controllers/parentCtrl.js' ,
                    'scripts/controllers/addCtrl.js' ,
                    'scripts/controllers/articleCtrl.js' ,
                    'scripts/controllers/articlesCtrl.js' ,
                    'scripts/controllers/modalCtrl.js' ,
                    'scripts/controllers/singleStorageCtrl.js' ,
                    'scripts/controllers/storageCtrl.js' ,
                    'scripts/controllers/subscribeCtrl.js' ,
                    'scripts/controllers/tagCtrl.js' ,

                    /* Directives */
                    'scripts/directives/directive.js' ,
                    'scripts/directives/jpadd.js' ,
                    'scripts/directives/jparticle.js' ,
                    'scripts/directives/jpcategory.js' ,
                    'scripts/directives/jpcategoryInput.js' ,
                    'scripts/directives/jpcontact.js' ,
                    'scripts/directives/jplogin.js' ,
                    'scripts/directives/jpmenu.js' ,
                    'scripts/directives/jpnode.js' ,
                    'scripts/directives/jppostarea.js' ,
                    'scripts/directives/mathjaxBind.js' ,
                    'scripts/directives/slideable.js' ,
                    'scripts/directives/slideToggle.js' ,

                    /* Directives
                     -----Validators*/
                    'scripts/directives/validators/jpalphanumeric.js' ,
                    'scripts/directives/validators/jpcharacters.js' ,

                    /* Factories */
                    'scripts/factories/factory.js' ,
                    'scripts/factories/jpcommon.js' ,
                    'scripts/factories/jpgenerator.js' ,

                    /* Services */
                    'scripts/services/service.js' ,
                    'scripts/services/jpartfilter.js' ,
                    'scripts/services/jpauth.js' ,
                    'scripts/services/jpvalidator.js' ,
                    'scripts/services/modalService.js'
                ],
                dest: 'scripts/output.js'
            }
        },

        ngAnnotate: {
            dist: {
                files: {
                    'scripts/output.js': 'scripts/output.js'
                }
            }
        },

        uglify: {
            my_target: {
                files: {
                    'scripts/output.min.js': 'scripts/output.js'
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.renameTask('concat', 'concatScss');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.renameTask('concat', 'concatCss');
    grunt.loadNpmTasks('grunt-contrib-sass');
    grunt.loadNpmTasks('grunt-purifycss');
    grunt.loadNpmTasks('grunt-autoprefixer');
    grunt.loadNpmTasks('grunt-contrib-cssmin');

    grunt.loadNpmTasks('grunt-angular-templates');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.renameTask('concat', 'concatJS');
    grunt.loadNpmTasks('grunt-ng-annotate');
    grunt.loadNpmTasks('grunt-contrib-uglify');

    grunt.registerTask('css', ['clean', 'concatScss', 'sass', 'concatCss', 'purifycss', 'autoprefixer', 'cssmin']);
    grunt.registerTask('js', ['ngtemplates', 'concatJS', 'ngAnnotate', 'uglify']);
    grunt.registerTask('all', ['css', 'js']);
};