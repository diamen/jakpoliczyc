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

    grunt.registerTask('css', ['clean', 'concatScss', 'sass', 'concatCss', 'purifycss', 'autoprefixer', 'cssmin']);
};