//
//  ViewController.m
//  ThriftSample
//
//  Created by Witz Hsiao on 8/2/15.
//  Copyright (c) 2015 LineCorp. All rights reserved.
//

#import "ViewController.h"

#import <TSocketClient.h>
#import <TBinaryProtocol.h>

#import "echo.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    TSocketClient *transport = [[TSocketClient alloc] initWithHostname:@"localhost" port:9090];
    TBinaryProtocol *protocol = [[TBinaryProtocol alloc] initWithTransport:transport];
    EchoSrvClient *client = [[EchoSrvClient alloc] initWithProtocol:protocol];
    
    @try {
        NSLog(@"echo response: %@", [client echo:@"hey"]);
        NSLog(@"echo2times response: %@", [client echo2times:@"hey"]);
    }
    @catch (NSException *exception) {
        NSLog(@"%@", [exception description]);
    }
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
