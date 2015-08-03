package echo

type EchoHandler struct {
}

func NewEchoHandler() *EchoHandler {
	return &EchoHandler{}
}

func (eh *EchoHandler) Echo(string1 string) (r string, err error) {
	return string1, nil
}

func (eh *EchoHandler) Echo2times(string1 string) (r string, err error) {
	return string1 + string1, nil
}
