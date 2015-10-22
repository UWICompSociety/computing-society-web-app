var gulp = require('gulp');
var postcss = require('gulp-postcss');
var csswring = require('csswring');
var sass = require('gulp-sass');
var autoprefixer = require('autoprefixer-core');
var cssnext = require('cssnext');
var lost = require('lost');
var inject = require('gulp-inject');

gulp.task('sass', function() {
    var processors = [
        lost
    ];

    return gulp.src('public/styles/**/*.scss')
        .pipe(sass())
        .pipe(postcss(processors))
        .pipe(autoprefixer({browsers:['last 2 version']}))
        .pipe(gulp.dest('public/styles/css'));

});

gulp.task('inject', function() {
    var sources = [
        'public/bower_components/**/*.min.js',
        'public/app/**/*.js',
        'public/styles/**/*.css',
        'public/bower_components/**/*.css'
    ];

    gulp.src('app/views/index.scala.html')
        .pipe(inject(gulp.src(sources,
            {read: false}),
            {ignorePath: 'public'},
            {transform: function(filepath) {
                var components = filepath.split('.');
                var newPath = '<script src="@routes.Assets.versioned("/public", "' + filepath + '")"';
                console.log(components[components.length ]);
                if (components[components.length ] == 'css')
                    return '<link rel="stylesheet" href="@routes.Assets.versioned("/public", "' + filepath + '")">';
                else if (components[components.length] == 'js')
                    return '<script src="@routes.Assets.versioned("/public", "' + filepath + '")" type="text/javascript"></script>';
                return inject.transform.apply(inject.transform, arguments);
            }
        }))
        .pipe(gulp.dest('app/views'))
});

//gulp.task('watch:styles', function() {
//    gulp.watch('**/*.css', ['styles']);
//});

gulp.task('sass:watch', function(){


    return gulp.watch(sources,
        ['sass']);
});

gulp.task('js:watch', function(){
    return gulp.watch([
            'public/bower_components/**/*.min.js',
            'public/app/**/*.js',
            'public/app/*.js'
        ],
        ['inject']);
});

gulp.task('default', function() {
    var sources = [
        'public/bower_components/**/*.min.js',
        'public/app/**/*.js',
        'public/app/*.js',
        'public/styles/**/*.css'
    ];

    gulp.run('inject');
    gulp.run('sass');

    gulp.watch(sources, ['sass', 'inject'])
});