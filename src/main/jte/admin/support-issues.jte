@param java.util.List<io.caniverse.investment.model.entity.SupportIssue> issues
@param io.caniverse.investment.model.entity.Investor investor
<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Support")
</head>
<body
        x-data="{ page: 'support', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.investor.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.admin-profile()
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">Support Issues</h2>
                <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">

                    <div class="grid grid-cols-3 gap-4 px-4 border-b mb-2">
                        <div class="col-span-1  items-center">
                            <p class="font-medium">Subject</p>
                        </div>
                        <div class="col-span-1  items-center">
                            <p class="font-medium">Investor</p>
                        </div>
                        <div class="col-span-1  items-center text-right">
                            <p class="font-medium">Status</p>
                        </div>

                    </div>

                    @for(var issue: issues)
                    <div class="grid grid-cols-3 gap-4 px-4">
                        <div class="col-span-1  items-center">
                            <div class="flex flex-col gap-4 sm:flex-row sm:items-center">

                                <p class="text-sm font-medium text-black dark:text-white">
                                    ${issue.getSubject()}<br>
                                    [ <a href="/admin/support/${issue.getId()}">View</a> ]
                                </p>
                            </div>
                        </div>
                        <div class="col-span-1  items-center">
                            <p class="text-sm font-medium text-black dark:text-white">
                                ${issue.getInvestor().getName()}
                            </p>
                        </div>
                        <div class="col-span-1  items-center  text-right">
                            <p class="inline-flex rounded-full ${issue.getStatus().equals(io.caniverse.investment.model.enums.SupportIssueStatus.OPEN) ? "bg-success text-success" : "bg-warning text-warning" }  bg-opacity-10 px-3 py-1 text-sm font-medium ">
                                ${issue.getStatus()}
                            </p>
                        </div>
                    </div>
                    @endfor


                </div>
            </div>
        </main>
    </div>
</div>
@template.partials.footer()
</body>

</html>