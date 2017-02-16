module.exports = function (grunt) {

    var concat = function () {
        var prefix = 'src/main/webapp/';
        var libs = [];
        for (var i = 0; i < files.length; i++) {
            libs.push(prefix + files[i]);
        }

        return libs;
    };

    var files = [
        /* Libraries */
        'libraries/angular/angular.min.js',
        'libraries/mspace/mspace.js',
        'libraries/ui-bootstrap/ui-bootstrap-tpls-2.0.0.min.js',
        'libraries/ui-router/angular-ui-router.min.js',
        'libraries/angular-mocks/angular-mocks.js',
        'libraries/angular-sanitize/angular-sanitize.js',
        'libraries/angular-ui-tree/dist/angular-ui-tree.js',
        'libraries/underscore/underscore.js',

        /* Browser support */
        'scripts/browsersupport.js',

        /* Common */
        'scripts/languages/PL.js',

        /* Main */
        'scripts/app.js',

        /* Generated by Grunt */
        'scripts/template.js',

        /* Config */
        'scripts/config/bootstrap.js',
        'scripts/config/routersConfig.js',
        'scripts/config/treeConfig.js',

        /* Dane testowe */
        'scripts/mockdata.js',

        /* Controllers */
        'scripts/controllers/controller.js',
        'scripts/controllers/parentCtrl.js',
        'scripts/controllers/addCtrl.js',
        'scripts/controllers/articleCtrl.js',
        'scripts/controllers/articlesCtrl.js',
        'scripts/controllers/modalCtrl.js',
        'scripts/controllers/singleStorageCtrl.js',
        'scripts/controllers/storageCtrl.js',
        'scripts/controllers/subscribeCtrl.js',
        'scripts/controllers/tagCtrl.js',

        /* Directives */
        'scripts/directives/directive.js',
        'scripts/directives/jpadd.js',
        'scripts/directives/jparticle.js',
        'scripts/directives/jpcategory.js',
        'scripts/directives/jpcategoryInput.js',
        'scripts/directives/jpcontact.js',
        'scripts/directives/jplogin.js',
        'scripts/directives/jpmenu.js',
        'scripts/directives/jpnode.js',
        'scripts/directives/jppostarea.js',
        'scripts/directives/mathjaxBind.js',
        'scripts/directives/slideable.js',
        'scripts/directives/slideToggle.js',

        /* Directives
         -----Validators*/
        'scripts/directives/validators/jpalphanumeric.js',
        'scripts/directives/validators/jpcharacters.js',

        /* Factories */
        'scripts/factories/factory.js',
        'scripts/factories/jpcommon.js',
        'scripts/factories/jpgenerator.js',

        /* Services */
        'scripts/services/service.js',
        'scripts/services/jpartfilter.js',
        'scripts/services/jpauth.js',
        'scripts/services/jpvalidator.js',
        'scripts/services/menuService.js',
        'scripts/services/modalService.js'
    ];

    grunt.initConfig({

       pkg: grunt.file.readJSON('package.json'),

       clean: ['src/main/webapp/sass/build.scss', 'src/main/webapp/styles/main.css', 'src/main/webapp/styles/main.css.map'],

       replace: {
         dist: {
             options: {
                 patterns: [
                     {
                         match: 'libraries',
                         replacement: files
                     }
                 ]
             },
             files: [{
                 src: ['src/main/webapp/scripts/libraries.preprocess'],
                 dest: 'src/main/webapp/scripts/libraries.js'
             }]
         }
       },

       concatScss: {
            dist: {
               src: [
                   'src/main/webapp/sass/*.scss'
               ],
               dest: 'src/main/webapp/sass/build.scss'
            }
       },

       sass: {
            dist: {
                options: {
                    style: 'expanded'
                },
                files: {
                    'src/main/webapp/styles/external/tmp.css': 'src/main/webapp/sass/build.scss'
                }
            }
       },

       concatCss: {
            dist: {
                src: [
                    'src/main/webapp/styles/external/*.css'
                ],
                dest: 'src/main/webapp/styles/main.css'
            }
       },

        purifycss: {
           options: {},
           target: {
               src: ['src/main/webapp/index.preprocess.html', 'src/main/webapp/views/*.html', 'src/main/webapp/views/partials/*.html', 'src/main/webapp/views/templates/*.html', 'src/main/webapp/scripts/directives/*.js', 'src/main/webapp/libraries/ui-bootstrap/*.js'],
               css: ['src/main/webapp/styles/main.css'],
               dest: 'src/main/webapp/styles/main.css'
           }
        },

       autoprefixer: {
            dist: {
                options: {
                    diff: false
                },
                src: 'src/main/webapp/styles/main.css'
            }
       },

       cssmin: {
           target: {
               files: [{
                   src: 'src/main/webapp/styles/main.css',
                   dest: 'src/main/webapp/styles/main.min.css'
               }]
           }
       },

        ngtemplates: {
          jakPoliczycApp: {
              src:  ['src/main/webapp/views/*.html', 'src/main/webapp/views/partials/*.html', 'src/main/webapp/views/templates/*.html'],
              dest: 'src/main/webapp/scripts/template.js'
          }
        },

        stringreplace: {
            dist: {
                files: [{
                    src: 'src/main/webapp/scripts/template.js',
                    dest: 'src/main/webapp/scripts/template.js'
                }],
                options: {
                    replacements: [{
                        pattern: /src\/main\/webapp\//g,
                        replacement: ''
                    }]
                }
            }
        },

        concatJS: {
            dist: {
                src: concat(),
                dest: 'src/main/webapp/scripts/output.js'
            }
        },

        ngAnnotate: {
            dist: {
                files: {
                    'src/main/webapp/scripts/output.js': 'src/main/webapp/scripts/output.js'
                }
            }
        },

        uglify: {
            my_target: {
                files: {
                    'src/main/webapp/scripts/output.min.js': 'src/main/webapp/scripts/output.js'
                }
            }
        },

        devpreprocess: {
            options: {
              context: {
                  NODE_ENV: 'development'
              }
            },
            html: {
                src: 'src/main/webapp/index.preprocess.html',
                dest: 'src/main/webapp/index.html'
            }
        },

        distpreprocess: {
            options: {
                context: {
                    NODE_ENV: 'production'
                }
            },
            html: {
                src: 'src/main/webapp/index.preprocess.html',
                dest: 'src/main/webapp/index.html'
            }
        }
    });

    grunt.loadNpmTasks('grunt-replace');
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
    grunt.loadNpmTasks('grunt-string-replace');
    grunt.renameTask('string-replace', 'stringreplace');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.renameTask('concat', 'concatJS');
    grunt.loadNpmTasks('grunt-ng-annotate');
    grunt.loadNpmTasks('grunt-contrib-uglify');

    grunt.loadNpmTasks('grunt-preprocess');
    grunt.renameTask('preprocess', 'devpreprocess');
    grunt.loadNpmTasks('grunt-preprocess');
    grunt.renameTask('preprocess', 'distpreprocess');

    grunt.registerTask('cssdev', ['clean', 'concatScss', 'sass', 'concatCss']);
    grunt.registerTask('css', ['cssdev', 'purifycss', 'autoprefixer', 'cssmin']);
    grunt.registerTask('js', ['ngtemplates', 'stringreplace', 'concatJS', 'ngAnnotate', 'uglify']);

    grunt.registerTask('dist', ['replace', 'css', 'js', 'distpreprocess']);
    grunt.registerTask('dev', ['replace', 'cssdev', 'devpreprocess']);
};