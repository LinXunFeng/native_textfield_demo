//
//  ViewController.m
//  ios_project
//
//  Created by LinXunFeng on 2023/7/8.
//

#import "ViewController.h"

@interface ViewController ()

@property (nonatomic, strong) UITextField *textField;

@end

@implementation ViewController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  
  [self.view addSubview:self.textField];

  // Listen text input
  [self.textField addTarget:self
                     action:@selector(textFieldDidChange:)
           forControlEvents:UIControlEventEditingChanged];
}


- (void)textFieldDidChange:(UITextField *)textField {
    if (textField.markedTextRange != nil) {
      // Did not click the Chinese characters on accessory view, keep typing.
      NSLog(@"markedTextRange: %@",textField.text);
    } else {
      // Commit text, which means that the text has been determined.
      // This is where you do things like make network requests or filter data.
      NSLog(@"text: %@", textField.text);
    }
}

- (UITextField *)textField {
    if (!_textField) {
        _textField = [[UITextField alloc] initWithFrame:CGRectMake(20, 100, 200, 50)];
        _textField.backgroundColor = [UIColor yellowColor];
    }
    return _textField;
}

@end
